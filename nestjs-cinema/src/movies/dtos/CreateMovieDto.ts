import { ApiProperty } from '@nestjs/swagger';

export class CreateMovieDto {
  @ApiProperty({ description: 'Título do filme' })
  title: string;

  @ApiProperty({ description: 'Descrição do filme' })
  description: string;

  @ApiProperty({ description: 'Data de lançamento do filme' })
  launchdate: Date;

  @ApiProperty({ type: [Date], description: 'Lista de horários de exibição do filme' })
  showtimes: Date[];
}
