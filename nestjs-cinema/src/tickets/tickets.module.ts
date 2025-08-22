import { Module } from '@nestjs/common';
import { TicketsService } from './tickets.service';
import { TicketsController } from './tickets.controller';
import { DBModule } from '../db/db.module';

@Module({
  imports: [DBModule],
  providers: [TicketsService],
  controllers: [TicketsController]
})
export class TicketsModule { }
