import { Component, OnInit } from '@angular/core';
import { MemberService, Member } from '../../services/member.service';
import { NgIf, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-member',
  standalone: true,
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css'],
  imports: [NgFor, FormsModule]
})
export class MemberComponent implements OnInit {
  members: Member[] = [];
  newMember: Member = { name: '', email: '', phoneNumber: '' };

  constructor(private memberService: MemberService) {}

  ngOnInit(): void {
    this.loadMembers();
  }

  loadMembers(): void {
    this.memberService.getAllMembers().subscribe(data => {
      this.members = data;
    });
  }

  addMember(): void {
    this.memberService.addMember(this.newMember).subscribe(() => {
      this.loadMembers();
      this.newMember = { name: '', email: '', phoneNumber: '' }; // Reset form
    });
  }

  deleteMember(id: number): void {
    this.memberService.deleteMember(id).subscribe(() => {
      this.loadMembers();
    });
  }
}
