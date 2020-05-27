insert into category (name) values ('Fantasy')
insert into category (name) values ('Novel')


insert into books (author, 'release', title, category_id) values ('J.K. Rowling', '1997-06-26', 'Harry Potter and the Philosopher''s Stone', 1)
insert into books (author, 'release', title, category_id) values ('J. R. R. Tolkien', '1954-06-29', 'The Fellowship of the Ring', 1)
insert into books (author, 'release', title, category_id) values ('Lucy Maud Montgomery', '1908-06-01', 'Anne of Green Gables', 2)


insert into reviews (content, score, book_id) values ('Really great', 5, 1)
insert into reviews (content, score, book_id) values ('Magnificent!', 5, 1)
insert into reviews (content, score, book_id) values ('The best', 5, 2)
insert into reviews (content, score, book_id) values ('My favourite', 5, 2)
insert into reviews (content, score, book_id) values ('I like it', 4, 3)
insert into reviews (content, score, book_id) values ('I dont like it', 1, 3)

-- password: 1234
insert into users (username, password, email) values ('krystian', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'krystian@gmail.com')
insert into users (username, password, email) values ('zaneta', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'zaneta@gmail.com')
insert into users (username, password, email) values ('szymon', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'szymon@gmail.com')
insert into users (username, password, email) values ('pavel', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'pavel@gmail.com')
insert into users (username, password, email) values ('wiktor', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'wiktor@gmail.com')
insert into users (username, password, email) values ('maria', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'maria@gmail.com')







