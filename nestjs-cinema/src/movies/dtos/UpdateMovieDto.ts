import { ApiProperty } from '@nestjs/swagger';
import { IsOptional, IsString, IsInt, IsPositive } from 'class-validator';

export class UpdateMovieDto {
  @ApiProperty({ description: 'Título do filme', required: false })
  @IsOptional()
  @IsString()
  title?: string;

  @ApiProperty({ description: 'Descrição do filme', required: false })
  @IsOptional()
  @IsString()
  description?: string;

  @ApiProperty({ description: 'Ano de lançamento do filme', required: false })
  @IsOptional()
  @IsInt()
  @IsPositive()
  releaseYear?: number;
}
