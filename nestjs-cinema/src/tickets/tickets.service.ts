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
    return this.ticketRepository.save(ticket);
  }

  async findAll() {
    return this.ticketRepository.find();
  }


  async findOne(id: number) {
    return this.ticketRepository.findOne({ where: { id } }); 
  }


  async update(id: number, ticket: Partial<Ticket>) {
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
