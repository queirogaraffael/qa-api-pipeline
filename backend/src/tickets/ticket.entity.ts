import { Entity, PrimaryGeneratedColumn, Column, ManyToOne } from 'typeorm';
import { Movie } from '../movies/movie.entity';
import { User } from '../users/user.entity'; // Importando a entidade User

@Entity()
export class Ticket {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  seatNumber: number;

  @Column()
  price: number;

  @Column({ type: 'date' })
  showtime: Date;

  @ManyToOne(() => Movie, movie => movie.tickets)
  movie: Movie;

  @ManyToOne(() => User, user => user.tickets) 
  user: User; 
}
