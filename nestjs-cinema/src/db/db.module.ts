import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Movie } from '../movies/movie.entity';
import { Ticket } from '../tickets/ticket.entity';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'sqlite',
      database: ':memory:', // Configura o banco em memória
      entities: [Movie, Ticket], // Define as entidades que o TypeORM usará
      synchronize: true, // Sincroniza automaticamente as entidades
    }),
    TypeOrmModule.forFeature([Movie, Ticket]), // Registra as entidades nos módulos que o importarem
  ],
  exports: [TypeOrmModule], // Exporta o TypeOrmModule para ser usado em outros módulos
})
export class DBModule {}
