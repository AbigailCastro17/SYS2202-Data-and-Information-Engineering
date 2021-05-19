tornado <- read.csv(file = 'tornado.csv')
tornado

d1<- tornado %>%  group_by(yr, f) %>% summarise(count = n())
d1