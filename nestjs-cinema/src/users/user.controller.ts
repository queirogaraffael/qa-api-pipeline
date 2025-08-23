import { Controller, Post, Body, Get, Param, Put, Delete, Query } from '@nestjs/common';
import { UserService } from './user.service';
import { CreateUserDto } from './CreateUserDto';
import { ApiBearerAuth, ApiTags, ApiOperation, ApiResponse, ApiParam, ApiBody, ApiQuery } from '@nestjs/swagger';
import {  UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from '../auth/jwt-auth.guard'; 
import { Roles } from '../auth/roles.decorator';
import { RolesGuard } from '../auth/roles.guard'; 

@ApiTags('users')
@Controller('users')
export class UserController {
  constructor(private readonly userService: UserService) { }

  @Post()
  @ApiOperation({ summary: 'Cria um novo usuário' })
  @ApiResponse({ status: 201, description: 'Usuário criado com sucesso.' })
  @ApiResponse({ status: 400, description: 'Dados inválidos.' })
  @ApiBody({
    description: 'Informações do usuário a ser criado',
    schema: {
      type: 'object',
      properties: {
        name: { type: 'string', example: 'João Silva' },
        email: { type: 'string', example: 'joao.silva@exemplo.com' },
        password: { type: 'string', example: 'senha123' },
        role: { type: 'string', example: 'user' },
      },
    },
  })
  async create(@Body() createUserDto: CreateUserDto) {
    return this.userService.createUser(createUserDto);
  }

  @Get()
  @UseGuards(JwtAuthGuard, RolesGuard)
  @Roles('admin')
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Lista todos os usuários com paginação' })
  @ApiResponse({
    status: 200,
    description: 'Lista de usuários paginada',
    schema: {
      example: {
        data: [
          {
            id: 1,
            username: 'usuarioExemplo',
            email: 'usuario@exemplo.com',
            createdAt: '2023-01-01T12:00:00Z',
          },
        ],
        total: 50,
        page: 1,
        lastPage: 5,
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
    description: 'Número de usuários por página',
    type: Number,
    example: 10,
  })
  async findAll(@Query('page') page = 1, @Query('limit') limit = 10) {
    return this.userService.findAll({ page, limit });
  }
  

  @Get(':id')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Busca um usuário pelo ID' })
  @ApiResponse({ status: 200, description: 'Usuário encontrado.' })
  @ApiResponse({ status: 404, description: 'Usuário não encontrado.' })
  @ApiParam({ name: 'id', description: 'ID do usuário', example: '12345' })
  async findOne(@Param('id') id: string) {
    return this.userService.findOneById(id);
  }

  @Put(':id')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Atualiza informações de um usuário pelo ID' })
  @ApiResponse({ status: 200, description: 'Usuário atualizado com sucesso.' })
  @ApiResponse({ status: 404, description: 'Usuário não encontrado.' })
  @ApiBody({
    description: 'Dados atualizados do usuário',
    schema: {
      type: 'object',
      properties: {
        name: { type: 'string', example: 'João Silva' },
        email: { type: 'string', example: 'joao.silva@exemplo.com' },
        password: { type: 'string', example: 'novaSenha123' },
        role: { type: 'string', example: 'adm' },
      },
    },
  })
  @ApiParam({ name: 'id', description: 'ID do usuário', example: '12345' })
  async update(@Param('id') id: string, @Body() updateUserDto: Partial<CreateUserDto>) {
    return this.userService.updateUser(id, updateUserDto);
  }

  @Delete(':id')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Remove um usuário pelo ID' })
  @ApiResponse({ status: 200, description: 'Usuário removido com sucesso.' })
  @ApiResponse({ status: 404, description: 'Usuário não encontrado.' })
  @ApiParam({ name: 'id', description: 'ID do usuário', example: '12345' })
  async delete(@Param('id') id: string) {
    return this.userService.deleteUser(id);
  }
}
