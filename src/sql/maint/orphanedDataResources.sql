SELECT id
FROM DATA_RESOURCE 
WHERE id NOT in (
      SELECT dr.id 
      FROM DATA_RESOURCE dr, CP cp 
      WHERE dr.id=cp.id 
      UNION
      SELECT dr.id 
      FROM DATA_RESOURCE dr, COMPARATOR c 
      WHERE dr.id=c.id 
      UNION
      SELECT dr.id 
      FROM DATA_RESOURCE dr, PERSON p 
      WHERE dr.id=p.id
      UNION
      SELECT dr.id 
      FROM DATA_RESOURCE dr, COMPETITOR c 
      WHERE dr.id=c.id
      UNION
      SELECT dr.id 
      FROM DATA_RESOURCE dr, COURSE c 
      WHERE dr.id=c.id
      UNION
      SELECT dr.id 
      FROM DATA_RESOURCE dr, DIVISION d 
      WHERE dr.id=d.id
      UNION
      SELECT dr.id 
      FROM DATA_RESOURCE dr, EVENT e 
      WHERE dr.id=e.id
      UNION
      SELECT dr.id 
      FROM DATA_RESOURCE dr, TEAM t 
      WHERE dr.id=t.id
      UNION
      SELECT dr.id 
      FROM DATA_RESOURCE dr, IDENTITY i 
      WHERE dr.id=i.id
);