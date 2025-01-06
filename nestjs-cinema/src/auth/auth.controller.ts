import {
  Controller,
  Post,
  Body,
  UsePipes,
  ValidationPipe, HttpCode
} from '@nestjs/common';
import { AuthService } from './auth.service';
import { ApiTags, ApiResponse, ApiOperation, ApiBody, } from '@nestjs/swagger';
import { LoginDto } from './dtos/LoginDto';
import { LoginResponse } from './dtos/login-response.dto';
import { EmailNotFoundException } from '../exceptions/email-not-found.exception';
import { InvalidPasswordException } from '../exceptions/invalid-password.exception';

@ApiTags('auth')
@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) { }

  @Post('login')
  @HttpCode(200)
  @ApiOperation({
    summary: 'Realizar login',
    description: 'Autentica o usuário e retorna um token Bearer.',
  })
  @ApiResponse({ status: 200, description: 'Login bem-sucedido.', type: LoginResponse })
  @ApiResponse({ status: 404, description: 'Email não encontrado.', type: EmailNotFoundException })
  @ApiResponse({ status: 401, description: 'Senha incorreta.', type: InvalidPasswordException })
  @ApiBody({
    description: 'Credenciais para autenticação',
    type: LoginDto,
    examples: {
      valid: {
        summary: 'Exemplo de credenciais válidas',
        value: { email: 'usuario@exemplo.com', password: 'senha123' },
      },
    },
  })
  @UsePipes(new ValidationPipe({ whitelist: true, forbidNonWhitelisted: true }))
  async login(@Body() loginDto: LoginDto) {
    const user = await this.authService.validateUser(loginDto.email, loginDto.password);
    return this.authService.login(user);
  }
}
