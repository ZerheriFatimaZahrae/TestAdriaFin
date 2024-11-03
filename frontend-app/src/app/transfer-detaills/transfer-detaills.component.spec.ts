import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferDetaillsComponent } from './transfer-detaills.component';

describe('TransferDetaillsComponent', () => {
  let component: TransferDetaillsComponent;
  let fixture: ComponentFixture<TransferDetaillsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TransferDetaillsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TransferDetaillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
