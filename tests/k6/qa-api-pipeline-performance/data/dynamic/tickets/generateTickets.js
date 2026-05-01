const { faker } = require('@faker-js/faker/locale/pt_BR');
const fs = require('fs');

const quantidade = process.argv[2] || 10;

const tickets = new Set();

while (tickets.size < quantidade) {
    const ticket = {
        movieId: faker.string.uuid(),
        userId: faker.string.uuid(),
        seatNumber: tickets.size + 1,
        price: faker.finance.amount(10, 100, 2),
        showtime: faker.date.future().toISOString()
    };

    tickets.add(JSON.stringify(ticket));
}

const data = {
    tickets: Array.from(tickets).map(ticket => JSON.parse(ticket))
};

fs.writeFileSync('tickets.json', JSON.stringify(data, null, 2), error => {
    if (error) {
        console.error(error);
    }
});
