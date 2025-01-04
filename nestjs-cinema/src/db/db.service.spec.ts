import { Test, TestingModule } from '@nestjs/testing';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Movie } from '../movies/movie.entity';
import { Ticket } from '../tickets/ticket.entity';
import { MoviesService } from '../movies/movies.service';

describe('MoviesService', () => {
  let service: MoviesService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      imports: [
        TypeOrmModule.forRoot({
          type: 'sqlite',
          database: ':memory:',
          entities: [Movie, Ticket],
          synchronize: true,
        }),
        TypeOrmModule.forFeature([Movie]),
      ],
      providers: [MoviesService],
    }).compile();

    service = module.get<MoviesService>(MoviesService);
  });

  it('should create and retrieve a movie', async () => {
    const movie = await service.create({
      title: 'Inception',
      description: 'A mind-bending thriller',
      launchdate: new Date('2010-07-16'),  // Alterado para 'launchdate'
      showtimes: [new Date('2025-01-01'), new Date('2025-01-02')], // Exemplo de showtimes
    });

    expect(movie.id).toBeDefined();

    const movies = await service.findAll();
    expect(movies).toHaveLength(1);
    expect(movies[0].title).toBe('Inception');
  });

});
