import { HttpException, HttpStatus } from '@nestjs/common';

export class EmailNotFoundException extends HttpException {
  constructor() {
    super('Email não encontrado.', HttpStatus.NOT_FOUND);
  }
}
