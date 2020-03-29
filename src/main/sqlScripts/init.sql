Create table WPCCombos (
    valueStream VARCHAR(50),
    productionLine VARCHAR(50),
    productType VARCHAR(50),
    PRIMARY KEY (valueStream , productionLine , productType)
);

Create table WPCs (
    valueStream VARCHAR(50),
    productionLine VARCHAR(50),
    productType VARCHAR(50),
    workPieceCarrierNumber int,
    PRIMARY KEY (valueStream , productionLine , productType, workPieceCarrierNumber),
    FOREIGN KEY (valueStream, productionLine, productType) REFERENCES WPCCombos(valueStream, productionLine, productType)
);

create table wpcFiles (
    valueStream VARCHAR(50),
    productionLine VARCHAR(50),
    productType VARCHAR(50),
    author VARCHAR(100),
    revisionNumber int,
    revisionReason VARCHAR(100),
    fileType VARCHAR(50),
    fileName VARCHAR(100),
    fileData MEDIUMBLOB,
    PRIMARY KEY (valueStream, productionLine, productType, author, revisionNumber, fileType, fileName),
    FOREIGN KEY (valueStream, productionLine, productType) REFERENCES WPCCombos(valueStream, productionLine, productType)
);

create table RepairCodes (
    valueStream VARCHAR(50),
    productionLine VARCHAR(50),
    repairCategory VARCHAR(100),
    repairDetail VARCHAR(100),
    PRIMARY KEY (valueStream, productionLine, repairCategory, repairDetail)
);

Create Table RepairTickets (
    valueStream VARCHAR(50),
    productionLine VARCHAR(50),
    productType VARCHAR(50),
    workPieceCarrierNumber int,
    repairCategory VARCHAR(100),
    repairDetail VARCHAR(100),
    extraInfo VARCHAR(100),
    userEntry VARCHAR(50),
    timeStampOpened VARCHAR(50),

    PRIMARY KEY (valueStream, productionLine, productType, workPieceCarrierNumber, repairCategory, repairDetail, userEntry, timeStampOpened),
    FOREIGN KEY (valueStream, productionLine, repairCategory, repairDetail) REFERENCES RepairCodes (valueStream, productionLine, repairCategory, repairDetail)
);

INSERT INTO RepairCodes(valueStream, productionLine, repairCategory, repairDetail) VALUES
 ('CRIN','Test Line 1','WPC Backflow Failure','Pneum Cyl NOK')
,('CRIN','Test Line 1','WPC Backflow Failure','Pneum Hose Leaking')
,('CRIN','Test Line 1','WPC Backflow Failure','Backflow Hose Leaking')
,('CRIN','Test Line 1','WPC Backflow Failure','Inj Contack Seal NOK')
,('CRIN','Test Line 1','WPC Backflow Failure','Missing Fitting Seal')
,('CRIN','Test Line 1','WPC Backflow Failure','Fitting NOK or Loose')
,('CRIN','Test Line 1','WPC Backflow Failure','Contact Block NOK')
,('CRIN','Test Line 1','WPC ID 80 / ID 40 Not Functioning','Station Faults Out')
,('CRIN','Test Line 1','WPC ID 80 / ID 40 Not Functioning','Bracket Broken')
,('CRIN','Test Line 1','WPC ID 80 / ID 40 Not Functioning','NOK at HP Leak')
,('CRIN','Test Line 1','WPC ID 80 / ID 40 Not Functioning','NOK at EMI')
,('CRIN','Test Line 1','WPC ID 80 / ID 40 Not Functioning','NOK at St. 7')
,('CRIN','Test Line 1','WPC ID 80 / ID 40 Not Functioning','NOK at St. 400')
,('CRIN','Test Line 1','WPC Base Failure','Base Plate Bent')
,('CRIN','Test Line 1','WPC Base Failure','WPC Sticks on Pins')
,('CRIN','Test Line 1','WPC Base Failure','Stripped Holes')
,('CRIN','Test Line 1','WPC Base Failure','Missing Bushing')
,('CRIN','Test Line 1','WPC Base Failure','Bushing NOT Flush')
,('CRIN','Test Line 1','WPC Base Failure','Loose Bumpers')
,('CRIN','Test Line 1','WPC Base Failure','Switch Exciter Plate NOK')
,('CRIN','Test Line 1','WPC Interference with Injector','WPC Bore tight with Injector')
,('CRIN','Test Line 1','WPC Interference with Injector','WPC Bore damaged at bore')
,('CRIN','Test Line 1','WPC Interference with Injector','Backflow Seal interference')
,('CRIN','Test Line 1','WPC Injector Latch NOK','Inj. Latch Slide Bent / Jammed')
,('CRIN','Test Line 1','WPC Injector Latch NOK','Inj. Latch Slide Detent NOK')
,('CRIN','Test Line 1','WPC Injector Latch NOK','Inj. Latch Slide Too Loose')
,('CRIN','Test Line 1','WPC High Press. Leakage','High Pressure Nipple Dented')
,('CRIN','Test Line 1','WPC High Press. Leakage','High Press Clamp Collar NOK')
,('CRIN','Test Line 1','WPC High Press. Leakage','Creates High Pressure Mist')
,('CRIN','Magnet Line 2','WPC Base Failure','Base Plate Bent')
,('CRIN','Magnet Line 2','WPC Base Failure','WPC Sticks on Pins')
,('CRIN','Magnet Line 2','WPC Base Failure','Stripped Holes')
,('CRIN','Magnet Line 2','WPC Base Failure','Missing Bushing')
,('CRIN','Magnet Line 2','WPC Base Failure','Bushing NOT Flush')
,('CRIN','Magnet Line 2','WPC Base Failure','Loose Bumpers')
,('CRIN','Magnet Line 2','WPC Base Failure','Switch Exciter Plate NOK')
,('CRIN','Magnet Line 2','WPC ID80','NOK St1')
,('CRIN','Magnet Line 2','WPC ID80','NOK St2')
,('CRIN','Magnet Line 2','WPC ID80','NOK St3')
,('CRIN','Magnet Line 2','WPC ID80','NOK St5')
,('CRIN','Magnet Line 2','WPC part placement','Crash at Oring installation')
,('CRIN','Magnet Line 2','WPC part placement','Crash at Laser')
,('CRIN','Magnet Line 2','WPC part placement','Crash at Resistnace');

INSERT INTO WPCCombos (valueStream, productionLine, productType) VALUES
  ('CRIN', 'Assembly Line 1', 'CRIN3'),
	('CRIN', 'Assembly Line 1', 'CRIN1'),
	('CRIN', 'Assembly Line 1', 'CAT'),
	('CRIN', 'Assembly Line 1', 'IVECO'),
	('CRIN', 'Assembly Line 1', 'DMAX'),
	('CRIN', 'Assembly Line 1', 'Tier3'),
	('CC', 'Assembly Line 1', 'CRIN3'),
	('CC', 'Assembly Line 1', 'CRIN1'),
	('CC', 'Assembly Line 1', 'CAT'),
	('CC', 'Assembly Line 1', 'IVECO'),
	('CC', 'Assembly Line 1', 'DMAX'),
	('CC', 'Assembly Line 1', 'Tier3'),
	('HDEV5', 'Assembly Line 1', 'CRIN3'),
	('HDEV5', 'Assembly Line 1', 'CRIN1'),
	('HDEV5', 'Assembly Line 1', 'CAT'),
	('HDEV5', 'Assembly Line 1', 'IVECO'),
	('HDEV5', 'Assembly Line 1', 'DMAX'),
	('HDEV5', 'Assembly Line 1', 'Tier3'),
	('HDEV6', 'Assembly Line 1', 'CRIN3'),
	('HDEV6', 'Assembly Line 1', 'CRIN1'),
	('HDEV6', 'Assembly Line 1', 'CAT'),
	('HDEV6', 'Assembly Line 1', 'IVECO'),
	('HDEV6', 'Assembly Line 1', 'DMAX'),
	('HDEV6', 'Assembly Line 1', 'Tier3'),
	('HPD5', 'Assembly Line 1', 'CRIN3'),
	('HPD5', 'Assembly Line 1', 'CRIN1'),
	('HPD5', 'Assembly Line 1', 'CAT'),
	('HPD5', 'Assembly Line 1', 'IVECO'),
	('HPD5', 'Assembly Line 1', 'DMAX'),
	('HPD5', 'Assembly Line 1', 'Tier3'),
	('CRIN', 'Final Assembly Line', 'CRIN3'),
	('CRIN', 'Final Assembly Line', 'CRIN1'),
	('CRIN', 'Final Assembly Line', 'CAT'),
	('CRIN', 'Final Assembly Line', 'IVECO'),
	('CRIN', 'Final Assembly Line', 'DMAX'),
	('CRIN', 'Final Assembly Line', 'Tier3'),
	('CC', 'Final Assembly Line', 'CRIN3'),
	('CC', 'Final Assembly Line', 'CRIN1'),
	('CC', 'Final Assembly Line', 'CAT'),
	('CC', 'Final Assembly Line', 'IVECO'),
	('CC', 'Final Assembly Line', 'DMAX'),
	('CC', 'Final Assembly Line', 'Tier3'),
	('HDEV5', 'Final Assembly Line', 'CRIN3'),
	('HDEV5', 'Final Assembly Line', 'CRIN1'),
	('HDEV5', 'Final Assembly Line', 'CAT'),
	('HDEV5', 'Final Assembly Line', 'IVECO'),
	('HDEV5', 'Final Assembly Line', 'DMAX'),
	('HDEV5', 'Final Assembly Line', 'Tier3'),
	('HDEV6', 'Final Assembly Line', 'CRIN3'),
	('HDEV6', 'Final Assembly Line', 'CRIN1'),
	('HDEV6', 'Final Assembly Line', 'CAT'),
	('HDEV6', 'Final Assembly Line', 'IVECO'),
	('HDEV6', 'Final Assembly Line', 'DMAX'),
	('HDEV6', 'Final Assembly Line', 'Tier3'),
	('HPD5', 'Final Assembly Line', 'CRIN3'),
	('HPD5', 'Final Assembly Line', 'CRIN1'),
	('HPD5', 'Final Assembly Line', 'CAT'),
	('HPD5', 'Final Assembly Line', 'IVECO'),
	('HPD5', 'Final Assembly Line', 'DMAX'),
	('HPD5', 'Final Assembly Line', 'Tier3'),
    ('CRIN', 'Magnet Line', 'CRIN3'),
	('CRIN', 'Magnet Line', 'CRIN1'),
	('CRIN', 'Magnet Line', 'CAT'),
	('CRIN', 'Magnet Line', 'IVECO'),
	('CRIN', 'Magnet Line', 'DMAX'),
	('CRIN', 'Magnet Line', 'Tier3'),
	('CC', 'Magnet Line', 'CRIN3'),
	('CC', 'Magnet Line', 'CRIN1'),
	('CC', 'Magnet Line', 'CAT'),
	('CC', 'Magnet Line', 'IVECO'),
	('CC', 'Magnet Line', 'DMAX'),
	('CC', 'Magnet Line', 'Tier3'),
	('HDEV5', 'Magnet Line', 'CRIN3'),
	('HDEV5', 'Magnet Line', 'CRIN1'),
	('HDEV5', 'Magnet Line', 'CAT'),
	('HDEV5', 'Magnet Line', 'IVECO'),
	('HDEV5', 'Magnet Line', 'DMAX'),
	('HDEV5', 'Magnet Line', 'Tier3'),
	('HDEV6', 'Magnet Line', 'CRIN3'),
	('HDEV6', 'Magnet Line', 'CRIN1'),
	('HDEV6', 'Magnet Line', 'CAT'),
	('HDEV6', 'Magnet Line', 'IVECO'),
	('HDEV6', 'Magnet Line', 'DMAX'),
	('HDEV6', 'Magnet Line', 'Tier3'),
	('HPD5', 'Magnet Line', 'CRIN3'),
	('HPD5', 'Magnet Line', 'CRIN1'),
	('HPD5', 'Magnet Line', 'CAT'),
	('HPD5', 'Magnet Line', 'IVECO'),
	('HPD5', 'Magnet Line', 'DMAX'),
	('HPD5', 'Magnet Line', 'Tier3'),
    ('CRIN', 'Test Line', 'CRIN3'),
	('CRIN', 'Test Line', 'CRIN1'),
	('CRIN', 'Test Line', 'CAT'),
	('CRIN', 'Test Line', 'IVECO'),
	('CRIN', 'Test Line', 'DMAX'),
	('CRIN', 'Test Line', 'Tier3'),
	('CC', 'Test Line', 'CRIN3'),
	('CC', 'Test Line', 'CRIN1'),
	('CC', 'Test Line', 'CAT'),
	('CC', 'Test Line', 'IVECO'),
	('CC', 'Test Line', 'DMAX'),
	('CC', 'Test Line', 'Tier3'),
	('HDEV5', 'Test Line', 'CRIN3'),
	('HDEV5', 'Test Line', 'CRIN1'),
	('HDEV5', 'Test Line', 'CAT'),
	('HDEV5', 'Test Line', 'IVECO'),
	('HDEV5', 'Test Line', 'DMAX'),
	('HDEV5', 'Test Line', 'Tier3'),
	('HDEV6', 'Test Line', 'CRIN3'),
	('HDEV6', 'Test Line', 'CRIN1'),
	('HDEV6', 'Test Line', 'CAT'),
	('HDEV6', 'Test Line', 'IVECO'),
	('HDEV6', 'Test Line', 'DMAX'),
	('HDEV6', 'Test Line', 'Tier3'),
	('HPD5', 'Test Line', 'CRIN3'),
	('HPD5', 'Test Line', 'CRIN1'),
	('HPD5', 'Test Line', 'CAT'),
	('HPD5', 'Test Line', 'IVECO'),
	('HPD5', 'Test Line', 'DMAX'),
	('HPD5', 'Test Line', 'Tier3');