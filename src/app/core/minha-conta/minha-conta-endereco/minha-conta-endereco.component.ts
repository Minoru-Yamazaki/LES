import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-minha-conta-endereco',
  templateUrl: './minha-conta-endereco.component.html',
  styleUrls: ['./minha-conta-endereco.component.css']
})
export class MinhaContaEnderecoComponent implements OnInit {

  rua!: string;
  cep!: string;
  cidade!: string;

  constructor() { }

  ngOnInit(): void {
    this.rua = "Rua Um Dois Três"
    this.cep = "12345-678"
    this.cidade = "Mogi das Cruzes"
  }

}
