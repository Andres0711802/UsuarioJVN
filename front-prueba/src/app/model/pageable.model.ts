
export class pageable{
    constructor(
        public number:number,
        public size :number,
        public totalPages:number,
        public totalElements:number,
        public paginaActual:number
        
    ){}
    
}