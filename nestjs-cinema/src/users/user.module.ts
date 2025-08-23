import { Module } from '@nestjs/common';
import { UserService } from './user.service';
import { UserController } from './user.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './user.entity';
import { DbService } from '../db/db.service'; 

@Module({
  imports: [TypeOrmModule.forFeature([User])],
  providers: [UserService, DbService],       
  controllers: [UserController],            
  exports: [UserService, DbService],       
})
export class UsersModule {}
