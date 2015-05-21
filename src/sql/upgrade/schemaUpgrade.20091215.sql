ALTER TABLE CP CHANGE `from_discipline` `from_discipline` ENUM('default','running','trekking','mountaineering','coasteering','biking','orienteering','paddling','swimming','ropes','riverboarding','inlineskating','scootering','specialchallenge')  DEFAULT 'default';
ALTER TABLE CP CHANGE `to_discipline` `to_discipline` ENUM('default','running','trekking','mountaineering','coasteering','biking','orienteering','paddling','swimming','ropes','riverboarding','inlineskating','scootering','specialchallenge')  DEFAULT 'default';


