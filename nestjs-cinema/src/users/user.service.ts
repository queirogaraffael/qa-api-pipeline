import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from './user.entity';
import { CreateUserDto } from './CreateUserDto';
import * as bcrypt from 'bcrypt';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(User)
    private userRepository: Repository<User>,
  ) { }

  async createUser(createUserDto: CreateUserDto): Promise<User> {
    const hashedPassword = await bcrypt.hash(createUserDto.password, 10);
    const newUser = this.userRepository.create({
      ...createUserDto,
      password: hashedPassword,
    });
    return this.userRepository.save(newUser);
  }

  async findAll({ page, limit }: { page: number; limit: number }): Promise<{ data: User[]; total: number; page: number; lastPage: number }> {
    const pageNum = Number(page) || 1; 
    const limitNum = Number(limit) || 10; 
  
    const [users, total] = await this.userRepository.findAndCount({
      skip: (pageNum - 1) * limitNum, 
      take: limitNum, 
    });
  
    return {
      data: users,
      total,
      page: pageNum,
      lastPage: Math.ceil(total / limitNum), 
    };
  }

  async findOneById(id: string): Promise<User | null> {
    return this.userRepository.findOne({ where: { id } });
  }

  async updateUser(id: string, updateUserDto: Partial<CreateUserDto>): Promise<User> {
    await this.userRepository.update(id, updateUserDto);
    return this.findOneById(id);
  }

  async deleteUser(id: string): Promise<void> {
    await this.userRepository.delete(id);
  }

  async findOneByEmail(email: string): Promise<User | null> {
    return this.userRepository.findOne({ where: { email } });
  }
}
