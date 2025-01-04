import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from 'typeorm';
import { Ticket } from '../tickets/ticket.entity';

@Entity()
export class Movie {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  title: string;

  @Column()
  description: string;

  @Column({ type: 'date' })
  launchdate: Date;

  @Column('simple-array')
  showtimes: Date[];

  @OneToMany(() => Ticket, ticket => ticket.movie)
  tickets: Ticket[];
}
