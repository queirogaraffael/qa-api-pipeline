import { NestFactory } from '@nestjs/core';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { ValidationPipe } from '@nestjs/common';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  const config = new DocumentBuilder()
    .setTitle('Cinema API')
    .setDescription('API para o gerenciamento completo de um cinema digital. Oferece funcionalidades para autenticação de usuários, cadastro e login, visualização e gerenciamento de filmes, compra de tickets e controle de sessões. Ideal para sistemas que buscam otimizar a experiência de usuários e administradores no contexto de cinemas. Baseada na API original disponível em https://github.com/juniorschmitz/nestjs-cinema, que foi melhorada e aprimorada para atender a novas demandas.')
    .addBearerAuth()
    .addTag('auth', 'Gerencia a autenticação do usuário, incluindo o login com credenciais e a geração de token Bearer')
    .addTag('users', 'Gerencia as operações de usuários, como criação, leitura, atualização e exclusão (CRUD)')
    .addTag('movies', 'Gerencia filmes no sistema, incluindo a criação, leitura, atualização e remoção (CRUD) de filmes')
    .addTag('tickets', 'Gerencia os tickets do sistema, incluindo a criação, leitura, atualização e remoção (CRUD) de tickets')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api/docs', app, document);

  app.useGlobalPipes(new ValidationPipe());

  await app.listen(3000);
}
bootstrap();
