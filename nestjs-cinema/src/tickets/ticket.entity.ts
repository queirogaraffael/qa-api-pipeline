import { Entity, PrimaryGeneratedColumn, Column, ManyToOne } from 'typeorm';
import { Movie } from '../movies/movie.entity';

@Entity()
export class Ticket {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  userId: string;

  @Column()
  seatNumber: number;

  @Column()
  price: number;

  @Column({ type: 'date' })
  showtime: Date;

  @ManyToOne(() => Movie, movie => movie.tickets)
  movie: Movie;
}
