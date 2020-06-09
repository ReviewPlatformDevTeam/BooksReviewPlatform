
insert into category (name) values ('Fantasy')
insert into category (name) values ('Novel')

insert into authors (name, description) values ('J.K. Rowling', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ultrices vulputate aliquam. Vestibulum rutrum, justo et ornare sagittis, ante lacus facilisis massa, vel finibus libero eros id urna. Maecenas malesuada et lorem ut sodales. Cras sit amet facilisis lectus. Suspendisse congue convallis libero, vehicula varius ligula interdum eu. Donec at mi faucibus nunc pretium egestas ut vitae nunc. Fusce eu risus vel diam egestas egestas. Nulla nec neque pharetra, pellentesque leo non, dictum sapien. Ut efficitur nunc eu augue ultricies, quis gravida quam cursus. Etiam malesuada tellus aliquam pellentesque semper. Praesent ultrices eros in consequat hendrerit. Pellentesque eget suscipit risus, et suscipit velit. Sed ultricies arcu vitae odio viverra porta.')
insert into authors (name, description) values ('J. R. R. Tolkien', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ultrices vulputate aliquam. Vestibulum rutrum, justo et ornare sagittis, ante lacus facilisis massa, vel finibus libero eros id urna. Maecenas malesuada et lorem ut sodales. Cras sit amet facilisis lectus. Suspendisse congue convallis libero, vehicula varius ligula interdum eu. Donec at mi faucibus nunc pretium egestas ut vitae nunc. Fusce eu risus vel diam egestas egestas. Nulla nec neque pharetra, pellentesque leo non, dictum sapien. Ut efficitur nunc eu augue ultricies, quis gravida quam cursus. Etiam malesuada tellus aliquam pellentesque semper. Praesent ultrices eros in consequat hendrerit. Pellentesque eget suscipit risus, et suscipit velit. Sed ultricies arcu vitae odio viverra porta.')
insert into authors (name, description) values ('Lucy Maud Montgomery', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ultrices vulputate aliquam. Vestibulum rutrum, justo et ornare sagittis, ante lacus facilisis massa, vel finibus libero eros id urna. Maecenas malesuada et lorem ut sodales. Cras sit amet facilisis lectus. Suspendisse congue convallis libero, vehicula varius ligula interdum eu. Donec at mi faucibus nunc pretium egestas ut vitae nunc. Fusce eu risus vel diam egestas egestas. Nulla nec neque pharetra, pellentesque leo non, dictum sapien. Ut efficitur nunc eu augue ultricies, quis gravida quam cursus. Etiam malesuada tellus aliquam pellentesque semper. Praesent ultrices eros in consequat hendrerit. Pellentesque eget suscipit risus, et suscipit velit. Sed ultricies arcu vitae odio viverra porta.')


insert into books (author_id, 'release', title, category_id) values (1, '1997-06-26', 'Harry Potter and the Philosopher''s Stone', 1)
insert into books (author_id, 'release', title, category_id) values (2, '1954-06-29', 'The Fellowship of the Ring', 1)
insert into books (author_id, 'release', title, category_id) values (3, '1908-06-01', 'Anne of Green Gables', 2)


insert into reviews (content, score, book_id, user_id) values ('Really great', 5, 1, 1)
insert into reviews (content, score, book_id, user_id) values ('Magnificent!', 5, 1, 2)
insert into reviews (content, score, book_id, user_id) values ('The best', 5, 2, 1)
insert into reviews (content, score, book_id, user_id) values ('My favourite', 5, 2, 3)
insert into reviews (content, score, book_id, user_id) values ('I like it', 4, 3, 4)
insert into reviews (content, score, book_id, user_id) values ('I dont like it', 1, 5, 5)

-- password: 1234
insert into users (username, password, email) values ('krystian', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'krystian@gmail.com')
insert into users (username, password, email) values ('zaneta', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'zaneta@gmail.com')
insert into users (username, password, email) values ('szymon', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'szymon@gmail.com')
insert into users (username, password, email) values ('pavel', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'pavel@gmail.com')
insert into users (username, password, email) values ('wiktor', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'wiktor@gmail.com')
insert into users (username, password, email) values ('maria', '$2a$10$NLnJWf3.ZQhXbUWja963s.Yc6/6GKSaOCsZ/Yvr1M6fqYKfKDnDxa', 'maria@gmail.com')







