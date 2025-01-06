import { Controller, Get, Post, Put, Delete, Body, Param, Query } from '@nestjs/common';
import { TicketsService } from './tickets.service';
import { Ticket } from './ticket.entity';
import { ApiBearerAuth, ApiTags, ApiResponse, ApiOperation, ApiQuery, } from '@nestjs/swagger';
import { JwtAuthGuard } from '../auth/jwt-auth.guard';
import { UseGuards } from '@nestjs/common';


@ApiTags('tickets')
@Controller('tickets')
@ApiBearerAuth()
@UseGuards(JwtAuthGuard)
export class TicketsController {
  constructor(private readonly ticketsService: TicketsService) { }

  @Post()
  @ApiOperation({ summary: 'Criar um novo ticket' })
  @ApiResponse({ status: 201, description: 'Ticket criado com sucesso.' })
  @ApiResponse({ status: 400, description: 'Requisição inválida.' })
  create(@Body() ticket: Ticket) {
    return this.ticketsService.create(ticket);
  }

  @Get()
  @ApiOperation({ summary: 'Listar todos os tickets com paginação' })
  @ApiResponse({
    status: 200,
    description: 'Tickets listados com paginação',
    schema: {
      example: {
        data: [
          {
            id: 1,
            title: 'Ticket Exemplo',
            description: 'Descrição do Ticket',
            status: 'Aberto',
            createdAt: '2023-01-01T12:00:00Z',
          },
        ],
        total: 20,
        page: 1,
        lastPage: 2,
      },
    },
  })
  @ApiQuery({
    name: 'page',
    required: false,
    description: 'Número da página',
    type: Number,
    example: 1,
  })
  @ApiQuery({
    name: 'limit',
    required: false,
    description: 'Número de tickets por página',
    type: Number,
    example: 10,
  })
  async findAll(@Query('page') page = 1, @Query('limit') limit = 10) {
    return this.ticketsService.findAll({ page, limit });
  }
  

  @Get(':id')
  @ApiOperation({ summary: 'Listar um ticket por ID' })
  @ApiResponse({ status: 200, description: 'Ticket listado com sucesso' })
  @ApiResponse({ status: 400, description: 'Requisição inválida.' })
  @ApiResponse({ status: 404, description: 'Ticket não encontrado' })
  findOne(@Param('id') id: string) {
    return this.ticketsService.findOne(parseInt(id));
  }

  @Put(':id')
  @ApiOperation({ summary: 'Atualizar um ticket' })
  @ApiResponse({ status: 200, description: 'Ticket atualizado com sucesso' })
  @ApiResponse({ status: 400, description: 'Requisição inválida.' })
  @ApiResponse({ status: 404, description: 'Ticket não encontrado' })
  update(@Param('id') id: string, @Body() ticket: Ticket) {
    return this.ticketsService.update(parseInt(id), ticket);
  }

  @Delete(':id')
  @ApiOperation({ summary: 'Deletar um ticket' })
  @ApiResponse({ status: 200, description: 'Ticket deletado com sucesso' })
  @ApiResponse({ status: 400, description: 'Requisição inválida.' })
  @ApiResponse({ status: 404, description: 'Ticket não encontrado' })
  remove(@Param('id') id: string) {
    return this.ticketsService.remove(parseInt(id));
  }
}
