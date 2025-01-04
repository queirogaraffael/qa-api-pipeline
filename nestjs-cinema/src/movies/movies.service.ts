import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Movie } from './movie.entity';
import { CreateMovieDto } from './dtos/CreateMovieDto';
import { UpdateMovieDto } from './dtos/UpdateMovieDto';
import { MovieDto } from './dtos/MovieDto';
import { plainToInstance } from 'class-transformer';

@Injectable()
export class MoviesService {
  constructor(
    @InjectRepository(Movie)
    private readonly movieRepository: Repository<Movie>,
  ) { }

  async create(movieDto: CreateMovieDto) {
    const movie = this.movieRepository.create(movieDto);
    return this.movieRepository.save(movie);
  }

  async findAll({ page, limit }: { page: number; limit: number }) {
    const [movies, total] = await this.movieRepository.findAndCount({
      skip: (page - 1) * limit,
      take: limit,
    });

    const movieDtos = plainToInstance(MovieDto, movies);

    return {
      data: movieDtos,
      total,
      page,
      lastPage: Math.ceil(total / limit),
    };
  }

  async findOne(id: number): Promise<MovieDto> {
    const movie = await this.movieRepository.findOne({ where: { id } });
    return plainToInstance(MovieDto, movie);
  }

  async update(id: number, movieDto: UpdateMovieDto) {
    await this.movieRepository.update(id, movieDto);
    const updatedMovie = await this.movieRepository.findOne({ where: { id } });
    return plainToInstance(MovieDto, updatedMovie);
  }

  async delete(id: number) {
    const movie = await this.movieRepository.findOne({ where: { id } });
    if (movie) {
      await this.movieRepository.remove(movie);
      return movie;
    }
    return null;
  }
}
