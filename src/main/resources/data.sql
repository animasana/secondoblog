insert into member(id, username, password, email, oauth, roles, created_date) values(default, 'gigi', '$2a$10$8Wf.JEp00BgRVNfAG87t8e8m1fZX1GhmusPB2GvvJPbq/2ajayxhG', 'gigi@gmail.com', 'kakao', 'ADMIN, MANAGER, USER', now());
insert into member(id, username, password, email, oauth, roles, created_date) values(default, 'yuki', '$2a$10$yHy6XT9x076jGs.whD19z.GNuZHjJ8vk2hIW5u17pe7GSeT6bjxx6', 'yuki@gmail.com', 'kakao', 'MANAGER, USER', now());
insert into member(id, username, password, email, oauth, roles, created_date) values(default, 'hana', '$2a$10$8Wf.JEp00BgRVNfAG87t8e8m1fZX1GhmusPB2GvvJPbq/2ajayxhG', 'hana@gmail.com', 'kakao', 'USER', now());

insert into board(id, title, content, count, member_id, created_date) values(default, '1', '1', 0, 1, now());
insert into board(id, title, content, count, member_id, created_date) values(default, '2', '2', 0, 2, now());
insert into board(id, title, content, count, member_id, created_date) values(default, '3', '3', 0, 3, now());

insert into reply(id, content, board_id, member_id, created_date) values(default, '첫번째 댓글', 1, 2, now());
insert into reply(id, content, board_id, member_id, created_date) values(default, '두번째 댓글', 1, 2, now());
insert into reply(id, content, board_id, member_id, created_date) values(default, '세번째 댓글', 1, 2, now());