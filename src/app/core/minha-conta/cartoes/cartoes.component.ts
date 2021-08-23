import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cartoes',
  templateUrl: './cartoes.component.html',
  styleUrls: ['./cartoes.component.css']
})
export class CartoesComponent implements OnInit {

  numero!: string;
  bandeira!: string;
  nome!: string;

  constructor() { }

  ngOnInit(): void {
    this.numero = "1234"
    this.bandeira = "MasterCard"
    this.nome = "Edson M Yamazaki"
  }

}
