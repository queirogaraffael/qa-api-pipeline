import { Injectable } from '@nestjs/common';  
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Ticket } from './ticket.entity';

@Injectable()
export class TicketsService {
  constructor(
    @InjectRepository(Ticket)  
    private readonly ticketRepository: Repository<Ticket>,
  ) {}

  async create(ticket: Partial<Ticket>) {
    const existingTicket = await this.ticketRepository.findOne({
      where: {
        seatNumber: ticket.seatNumber,
        showtime: ticket.showtime,
        movie: { id: ticket.movie.id },
      },
    });

    if (existingTicket) {
      throw new Error('Assento já ocupado para este filme e showtime');
    }

    return this.ticketRepository.save(ticket);
  }

  async findAll({ page, limit }: { page: number; limit: number }) {
    const [tickets, total] = await this.ticketRepository.findAndCount({
      skip: (page - 1) * limit,
      take: limit,
    });
  
    return {
      data: tickets,
      total,
      page,
      lastPage: Math.ceil(total / limit),
    };
  }  

  async findOne(id: number) {
    return this.ticketRepository.findOne({ where: { id } }); 
  }

  async update(id: number, ticket: Partial<Ticket>) {
    const existingTicket = await this.ticketRepository.findOne({
      where: {
        seatNumber: ticket.seatNumber,
        showtime: ticket.showtime,
        movie: { id: ticket.movie.id },
      },
    });

    if (existingTicket && existingTicket.id !== id) {
      throw new Error('Assento já ocupado para este filme e showtime');
    }

    await this.ticketRepository.update(id, ticket);
    return this.ticketRepository.findOne({ where: { id } }); 
  }

  async remove(id: number) {
    const ticket = await this.ticketRepository.findOne({ where: { id } });  
    if (ticket) {
      await this.ticketRepository.remove(ticket);
      return ticket;
    }
    return null;
  }
}
