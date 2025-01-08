const { faker } = require('@faker-js/faker/locale/pt_BR');
const fs = require('fs');

const quantidade = process.argv[2] || 10;

const filmes = [];

for (let i = 0; i < quantidade; i++) {
    const filme = {
        title: faker.lorem.words(3),
        description: faker.lorem.sentence(),
        launchdate: faker.date.future().toISOString(),
        showtimes: [
            faker.date.soon().toISOString(),
            faker.date.soon(1).toISOString()
        ]
    };
    filmes.push(filme);
}

const data = {
    movies: filmes
};

fs.writeFileSync('movies.json', JSON.stringify(data, null, 2), error => {
    if (error) {
        console.error(error);
    }
});
