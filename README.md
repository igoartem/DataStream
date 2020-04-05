# DataStream


Для работы приложения нужно установить kafka.
Инструкция по установке 
https://kafka.apache.org/quickstart

kafka должна быть в /home/kafka/kafka


tar xzf kafka_2.13-2.4.0.tgz
mv kafka_2.13-2.4.0 /usr/local/kafka

Добавить пользователя kafka
sudo useradd kafka -m

Установите и подтвердите пароль:
sudo passwd kafka

Добавьте созданного пользователя к группе sudo:
sudo adduser kafka sudo

Скачать kafka
curl "https://downloads.apache.org/kafka/2.4.1/kafka_2.11-2.4.1.tgz" -o ~/Downloads/kafka.tgz

mkdir ~/kafka
cd ~/kafka
 
Распокавать архив
tar -xvzf ~/Downloads/kafka.tgz --strip 1


Настройка сервера Kafka
Добавьте параметр, который позволяет удалять темы Kafka. Добавьте в конец файла следующее:
nano ~/kafka/config/server.properties
delete.topic.enable = true

Для того, чтобы kafka включался при загрузке сервера, выполните:

Теперь сервер Kafka запущен и прослушивает порт 9092.
Для того, чтобы kafka включался при загрузке сервера, выполните
sudo systemctl enable kafka


