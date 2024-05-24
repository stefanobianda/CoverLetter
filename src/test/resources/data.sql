MERGE INTO `roles` (`id`, `name`) KEY (`id`)  VALUES ('1', 'ROLE_USER');
MERGE INTO `roles` (`id`, `name`) KEY (`id`)  VALUES ('2', 'ROLE_ADMIN');
MERGE INTO `users` (`id`, `first_name`, `last_name`, `password`, `username`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`) KEY (`id`)  VALUES ('1000', 'Test', 'Admin', '$2a$10$gYjZD6tXhT4XJ2WB8ljSAOr7GDuo1c5TP7gZCA6xpYKkUR5F.EBWa', 'admin@test.com', true, true, true, true);
MERGE INTO `users_roles` (`user_id`, `role_id`) VALUES ('1000', '1');
