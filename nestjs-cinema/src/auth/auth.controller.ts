import { Controller, Post, Body, UnauthorizedException } from '@nestjs/common';
import { AuthService } from './auth.service';
import { ApiTags, ApiResponse, ApiOperation, ApiBody } from '@nestjs/swagger';
import { LoginDto } from './LoginDto';
import { LoginResponse } from './login-response.dto';

@ApiTags('auth')
@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) { }

  @Post('login')
  @ApiOperation({ summary: 'Realizar login', description: 'Autentica o usuário e retorna um token Bearer.' })
  @ApiResponse({ status: 200, description: 'Login bem-sucedido.', type: LoginResponse })
  @ApiResponse({ status: 401, description: 'Credenciais inválidas.' })
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
  async login(@Body() loginDto: LoginDto) {
    const user = await this.authService.validateUser(loginDto.email, loginDto.password);

    if (!user) {
      throw new UnauthorizedException('Credenciais inválidas');
    }

    return this.authService.login(user);
  }
}
