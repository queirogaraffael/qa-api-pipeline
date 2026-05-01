import { ApiProperty } from '@nestjs/swagger';

export class PaginatedResponseDto<T> {
  @ApiProperty({ description: 'Dados retornados', isArray: true })
  data: T[];

  @ApiProperty({ description: 'Total de itens disponíveis' })
  total: number;

  @ApiProperty({ description: 'Página atual' })
  page: number;

  @ApiProperty({ description: 'Número da última página' })
  lastPage: number;
}
