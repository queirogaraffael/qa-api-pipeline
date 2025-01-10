import { ApiProperty } from '@nestjs/swagger';

export class MovieDto {
  @ApiProperty({ description: 'ID do filme' })
  id: number;

  @ApiProperty({ description: 'Título do filme' })
  title: string;

  @ApiProperty({ description: 'Descrição do filme' })
  description: string;

  @ApiProperty({ description: 'Data de lançamento do filme', type: String, format: 'date' })
  launchdate: Date;

  @ApiProperty({ description: 'Horários de exibição do filme', type: [String] })
  showtimes: Date[];
}
