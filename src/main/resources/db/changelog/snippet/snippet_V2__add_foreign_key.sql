alter table if exists pastebin.snippets
add constraint FK
foreign key (author)
references pastebin.users (email)
