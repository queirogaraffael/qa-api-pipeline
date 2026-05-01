export function geraTicketAleatorio() {
    const randomIntBetween = (min, max) => Math.floor(Math.random() * (max - min + 1)) + min;
    const generateUniqueId = () => Math.random().toString(36).substring(2, 8);

    const ticket = {
        movieId: `movie-${generateUniqueId()}`,
        userId: `user-${generateUniqueId()}`,
        seatNumber: randomIntBetween(1, 100), 
        price: randomIntBetween(10, 60),
        showtime: new Date(Date.now() + randomIntBetween(3600000, 86400000)).toISOString(), 
    };

    return ticket;
}
