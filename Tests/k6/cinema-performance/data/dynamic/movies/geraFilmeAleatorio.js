export function geraFilmeAleatorio() {
    const randomIntBetween = (min, max) => Math.floor(Math.random() * (max - min + 1)) + min;
    
    const generateUniqueTitle = () => `Filme-${Math.random().toString(36).substring(2, 8)}_${Date.now()}`;
    
    const filme = {
        title: generateUniqueTitle(),
        description: `Descrição do filme ${randomIntBetween(1, 1000)}`,
        launchdate: new Date().toISOString(),
        showtimes: [`${randomIntBetween(10, 22)}:${randomIntBetween(0, 59)}`],
    };
    
    return filme;
}
