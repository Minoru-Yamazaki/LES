import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-input-mb3',
  templateUrl: './input-mb3.component.html',
  styleUrls: ['./input-mb3.component.css']
})
export class InputMb3Component implements OnInit {

  @Input() placeholder!: string;
  @Input() txtId!: string;
  @Input() name!: string;
  @Input() type!: string;
  
  constructor() { }

  ngOnInit(): void {
  }

}
