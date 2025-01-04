import { Module } from '@nestjs/common';
import { MoviesService } from './movies.service';
import { MoviesController } from './movies.controller';
import { DBModule } from '../db/db.module';  // Certifique-se de que o caminho está correto

@Module({
  imports: [DBModule],  // DBModule está sendo importado corretamente
  providers: [MoviesService],
  controllers: [MoviesController]
})
export class MoviesModule {}
