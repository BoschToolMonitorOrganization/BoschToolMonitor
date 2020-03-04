Create table WPCs (
    valueStream VARCHAR(25),
    productionLine VARCHAR(50),
    productType VARCHAR(40),
    workPieceCarrierNumber int,
    PRIMARY KEY (valueStream , productionLine , productType, workPieceCarrierNumber)
);

Create table WPCCombos (
    valueStream VARCHAR(25),
    productionLine VARCHAR(50),
    productType VARCHAR(40),
    PRIMARY KEY (valueStream , productionLine , productType)
);

INSERT INTO WPCCombos (valueStream, productionLine, productType)
VALUES
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