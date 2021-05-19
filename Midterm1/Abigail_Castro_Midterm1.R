library(tidyverse)
library(dplyr)
summary(esoph)
d1<- esoph %>%  group_by(agegp) %>%
  summarise(count = n(), total_cases = sum(ncases), total_controls = sum(ncontrols),
            percentage=total_cases*100/total_controls)
d1

ggplot(d1, aes(x=agegp, y=percentage,fill=agegp)) +
  geom_bar(stat="identity") +
  labs(x= 'Age Groups', y= 'Percentage Of Cancer Cases')

d2<- esoph %>%  group_by(agegp,tobgp) %>%
  summarise(count = n(), total_cases = sum(ncases), total_controls = sum(ncontrols),
            percentage_tob=total_cases*100/total_controls)
d2

d3<- esoph %>%  group_by(agegp,alcgp) %>%
  summarise(count = n(), total_cases = sum(ncases), total_controls = sum(ncontrols),
            percentage_alc=total_cases*100/total_controls)
view(d2)

d4 <- d2
d4["percentage_alc"] <- d3["percentage_alc"]
view(d4)
ggplot(d4, aes(x=percentage_tob, y=percentage_alc,color=agegp)) +
  geom_point(stat="identity") +
  geom_line()+
  labs(x= 'Percentage Of Cancer Cases', y= 'Percentage Of Cancer Cases')

ggplot(esoph, aes(x=ncontrols, y=ncases,color=agegp)) +
  geom_point() +
  geom_smooth(fill = NA) +
  labs(x= 'ncontrols', y= 'ncases')
