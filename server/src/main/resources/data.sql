
insert into category (name) values ('Fantasy')
insert into category (name) values ('Novel')

insert into authors (name, description) values ('J.K. Rowling', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ultrices vulputate aliquam. Vestibulum rutrum, justo et ornare sagittis, ante lacus facilisis massa, vel finibus libero eros id urna. Maecenas malesuada et lorem ut sodales. Cras sit amet facilisis lectus. Suspendisse congue convallis libero, vehicula varius ligula interdum eu. Donec at mi faucibus nunc pretium egestas ut vitae nunc. Fusce eu risus vel diam egestas egestas. Nulla nec neque pharetra, pellentesque leo non, dictum sapien. Ut efficitur nunc eu augue ultricies, quis gravida quam cursus. Etiam malesuada tellus aliquam pellentesque semper. Praesent ultrices eros in consequat hendrerit. Pellentesque eget suscipit risus, et suscipit velit. Sed ultricies arcu vitae odio viverra porta.')
insert into authors (name, description) values ('J. R. R. Tolkien', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ultrices vulputate aliquam. Vestibulum rutrum, justo et ornare sagittis, ante lacus facilisis massa, vel finibus libero eros id urna. Maecenas malesuada et lorem ut sodales. Cras sit amet facilisis lectus. Suspendisse congue convallis libero, vehicula varius ligula interdum eu. Donec at mi faucibus nunc pretium egestas ut vitae nunc. Fusce eu risus vel diam egestas egestas. Nulla nec neque pharetra, pellentesque leo non, dictum sapien. Ut efficitur nunc eu augue ultricies, quis gravida quam cursus. Etiam malesuada tellus aliquam pellentesque semper. Praesent ultrices eros in consequat hendrerit. Pellentesque eget suscipit risus, et suscipit velit. Sed ultricies arcu vitae odio viverra porta.')
insert into authors (name, description) values ('Lucy Maud Montgomery', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ultrices vulputate aliquam. Vestibulum rutrum, justo et ornare sagittis, ante lacus facilisis massa, vel finibus libero eros id urna. Maecenas malesuada et lorem ut sodales. Cras sit amet facilisis lectus. Suspendisse congue convallis libero, vehicula varius ligula interdum eu. Donec at mi faucibus nunc pretium egestas ut vitae nunc. Fusce eu risus vel diam egestas egestas. Nulla nec neque pharetra, pellentesque leo non, dictum sapien. Ut efficitur nunc eu augue ultricies, quis gravida quam cursus. Etiam malesuada tellus aliquam pellentesque semper. Praesent ultrices eros in consequat hendrerit. Pellentesque eget suscipit risus, et suscipit velit. Sed ultricies arcu vitae odio viverra porta.')


insert into books (author_id, 'release', title, category_id) values (1, '1997-06-26', 'Harry Potter and the Philosopher''s Stone', 1)
insert into books (author_id, description, 'release', title, category_id) values (1, 'When a troubled model falls to her death from a snow-covered Mayfair balcony, it is assumed that she has committed suicide. However, her brother has his doubts, and calls in private investigator Cormoran Strike to look into the case.',  '1997-06-26', 'The Cuckoo’s Calling', 1)
insert into books (author_id, description, 'release', title, category_id) values (1, 'The Tales of Beedle the Bard have been favourite bedtime reading in wizarding households for centuries. Full of magic and trickery, these classic tales both entertain and instruct, and remain as captivating to young wizards today as they were when Beedle first put quill to parchment in the fifteenth century. There are five tales in all: ‘The Tale of the Three Brothers‘ Harry Potter fans will know from Harry Potter and the Deathly Hallows; ‘The Fountain of Fair Fortune‘, ‘The Warlock’s Hairy Heart‘, ‘The Wizard and the Hopping Pot‘ and ‘Babbitty Rabbitty and her Cackling Stump‘ complete the collection. These narrative gems are accompanied by explanatory notes by Professor Albus Dumbledore (included by kind permission of the Hogwarts Headmaster’s archive). His illuminating thoughts reveal the stories to be much more than just simple moral tales.','1908-06-01', 'The Tales of Beedle the Bard', 1)
insert into books (author_id, description, 'release', title, category_id) values (2, 'The Lord of the Rings: The Fellowship of the Ring is a 2001 epic fantasy adventure film directed by Peter Jackson, based on the first volume of J. R. R. Tolkiens The Lord of the Rings. The film is the first instalment in The Lord of the Rings trilogy. It was produced by Barrie M. Osborne, Jackson, Fran Walsh and Tim Sanders, and written by Walsh, Philippa Boyens and Jackson.' ,'1954-06-29', 'The Fellowship of the Ring', 1)
insert into books (author_id, description, 'release', title, category_id) values (3, 'Anne of Green Gables is a 1908 novel by Canadian author Lucy Maud Montgomery (published as L.M. Montgomery). Written for all ages, it has been considered a classic children''s novel since the mid-twentieth century. Set in the late 19th century, the novel recounts the adventures of Anne Shirley, an 11-year-old orphan girl, who is mistakenly sent to two middle-aged siblings, Matthew and Marilla Cuthbert, who had originally intended to adopt a boy to help them on their farm in the fictional town of Avonlea on Prince Edward Island, Canada. The novel recounts how Anne makes her way through life with the Cuthberts, in school, and within the town.','1908-06-01', 'Anne of Green Gables', 2)
insert into books (author_id, description, 'release', title, category_id) values  (3, 'The Da Vinci Code is a 2003 mystery thriller novel by Dan Brown. It is Brown''s second novel to include the character Robert Langdon: the first was his 2000 novel Angels & Demons. The Da Vinci Code follows "symbologist" Robert Langdon and cryptologist Sophie Neveu after a murder in the Louvre Museum in Paris causes them to become involved in a battle between the Priory of Sion and Opus Dei over the possibility of Jesus Christ and Mary Magdalene having had a child together.', '1900-01-01', 'Da Vinci Code trilogy', 1)
