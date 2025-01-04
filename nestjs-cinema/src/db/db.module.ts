import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Movie } from '../movies/movie.entity';
import { Ticket } from '../tickets/ticket.entity';
import { User } from '../users/user.entity'; 

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'sqlite',
      database: ':memory:', 
      entities: [Movie, Ticket, User], 
      synchronize: true, 
    }),
    TypeOrmModule.forFeature([Movie, Ticket, User]), 
  ],
  exports: [TypeOrmModule],
})
export class DBModule {}
