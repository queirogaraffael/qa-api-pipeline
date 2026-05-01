import { ApiProperty } from '@nestjs/swagger';

export class LoginResponse {
  @ApiProperty({ description: 'Token JWT gerado após login bem-sucedido' })
  access_token: string;
}
