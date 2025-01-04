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

  // Método para criar um ingresso
  async create(ticket: Partial<Ticket>) {
    return this.ticketRepository.save(ticket);
  }

  // Método para encontrar todos os ingressos
  async findAll() {
    return this.ticketRepository.find();
  }

  // Método para encontrar um ingresso por ID
  async findOne(id: number) {
    return this.ticketRepository.findOne({ where: { id } });  // Alterado para usar 'where'
  }

  // Método para atualizar um ingresso
  async update(id: number, ticket: Partial<Ticket>) {
    await this.ticketRepository.update(id, ticket);
    return this.ticketRepository.findOne({ where: { id } });  // Alterado para usar 'where'
  }

  // Método para deletar um ingresso
  async remove(id: number) {
    const ticket = await this.ticketRepository.findOne({ where: { id } });  // Alterado para usar 'where'
    if (ticket) {
      await this.ticketRepository.remove(ticket);
      return ticket;
    }
    return null;
  }
}
