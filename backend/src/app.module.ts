import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { MoviesModule } from './movies/movies.module';
import { TicketsModule } from './tickets/tickets.module';
import { UsersModule } from './users/user.module';
import { AuthModule } from './auth/auth.module';


@Module({
  imports: [AuthModule, UsersModule, MoviesModule, TicketsModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule { }
