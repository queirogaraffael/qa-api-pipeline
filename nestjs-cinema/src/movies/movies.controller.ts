import { Controller, Get, Param, Post, Body, Put, Delete, HttpException, HttpStatus, Query } from '@nestjs/common';
import { MoviesService } from './movies.service';
import { ApiOperation, ApiResponse, ApiTags } from '@nestjs/swagger';
import { CreateMovieDto } from './dtos/CreateMovieDto';
import { UpdateMovieDto } from './dtos/UpdateMovieDto';
import { MovieDto } from './dtos/MovieDto';

@ApiTags('movies')
@Controller('movies')
export class MoviesController {
  constructor(private readonly moviesService: MoviesService) { }

  @Post()
  @ApiOperation({ summary: 'Cria um novo filme' })
  @ApiResponse({ status: 201, description: 'Filme criado com sucesso', type: MovieDto })
  @ApiResponse({ status: 400, description: 'Erro ao criar o filme' })
  async create(@Body() movie: CreateMovieDto) {
    try {
      return this.moviesService.create(movie);
    } catch (error) {
      throw new HttpException('Erro ao criar o filme', HttpStatus.BAD_REQUEST);
    }
  }

  @Get()
  @ApiOperation({ summary: 'Obtém todos os filmes com paginação' })
  @ApiResponse({
    status: 200,
    description: 'Lista de filmes paginada',
    schema: {
      example: {
        data: [
          {
            id: 1,
            title: 'Filme Exemplo',
            description: 'Descrição do Filme',
            launchdate: '2023-01-01',
            showtimes: ['2023-01-01T14:00:00Z', '2023-01-01T18:00:00Z'],
          },
        ],
        total: 10,
        page: 1,
        lastPage: 1,
      },
    },
  })
  async findAll(@Query('page') page = 1, @Query('limit') limit = 10) {
    return this.moviesService.findAll({ page, limit });
  }

  @Get(':id')
  @ApiOperation({ summary: 'Obtém um filme pelo ID' })
  @ApiResponse({ status: 200, description: 'Filme encontrado', type: MovieDto })
  @ApiResponse({ status: 404, description: 'Filme não encontrado' })
  async findOne(@Param('id') id: string) {
    const movieId = parseInt(id);
    const movie = await this.moviesService.findOne(movieId);
    if (!movie) {
      throw new HttpException('Filme não encontrado', HttpStatus.NOT_FOUND);
    }
    return movie;
  }

  @Put(':id')
  @ApiOperation({ summary: 'Atualiza um filme pelo ID' })
  @ApiResponse({ status: 200, description: 'Filme atualizado', type: UpdateMovieDto })
  @ApiResponse({ status: 404, description: 'Filme não encontrado para atualização' })
  async update(@Param('id') id: string, @Body() movie: UpdateMovieDto) {
    const movieId = parseInt(id);
    const updatedMovie = await this.moviesService.update(movieId, movie);
    if (!updatedMovie) {
      throw new HttpException('Filme não encontrado para atualização', HttpStatus.NOT_FOUND);
    }
    return updatedMovie;
  }

  @Delete(':id')
  @ApiOperation({ summary: 'Deleta um filme pelo ID' })
  @ApiResponse({ status: 204, description: 'Filme deletado com sucesso' })
  @ApiResponse({ status: 404, description: 'Filme não encontrado para exclusão' })
  async delete(@Param('id') id: string) {
    const movieId = parseInt(id);
    const deletedMovie = await this.moviesService.delete(movieId);
    if (!deletedMovie) {
      throw new HttpException('Filme não encontrado para exclusão', HttpStatus.NOT_FOUND);
    }
    return;
  }
}
