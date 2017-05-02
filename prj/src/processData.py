'''
@author: Michael Guarino
desc:
notes:
'''

import re, os
import itertools, operator
import numpy as np
from nltk.tokenize import StanfordTokenizer
from utils import DATA_DIR

class processData:

    def __init__(self):
        '''
        desc:
        args:
        returns:
        '''
    #end

    def _readFile(self, file):
        '''
        desc:
        args:
        returns:
        '''
        with open(file) as data:
            txtDoc = data.read()
        txtDoc = re.sub('[.!?]\s+', '<eos> <sos>', txtDoc)
        txtDoc = re.sub('[,-]', '', txtDoc)
        return txtDoc
    #end

    def _buildVocLookUp(self, txtDocTok):
        '''
        desc:
        args:
        returns:
        '''
        unqVoc = sorted(set(txtDocTok))
        unqVoc_LookUp = {v:k for k,v in enumerate(unqVoc)}
        return unqVoc_LookUp
    #end

    def _oneHotEncode(self, txtDocTok, unqVoc_LookUp):
        '''
        desc: one hot encodes entire document
        args:
        returns:
        '''
        maxVal = sorted(unqVoc_LookUp.items(), key=operator.itemgetter(1))[-1][1]
        txtDocOHE = list()
        for tok in txtDocTok:
            tokOHE = np.zeros(maxVal)
            val = unqVoc_LookUp[tok]
            for i in range(maxVal):
                if(i == val):
                    tokOHE[val] = 1
                    txtDocOHE.append(tokOHE)
                    break
        return txtDocOHE
    #end

    def oneHotDecode(self, inputOHE, unqVoc_LookUp):
        '''
        desc: decodes single one hot encoded input value
        args:
        returns:
        '''
        indexOfclass = np.argmax(inputOHE)
        decodedClass = list(unqVoc_LookUp.keys())[list(unqVoc_LookUp.values()).index(indexOfclass)]
        return decodedClass
    #end

    def dtm_builder(self):
        '''
        desc:
        args:
        returns:
        '''
        dataFiles = ['{}/{}'.format(DATA_DIR, file) for file in os.listdir(DATA_DIR) if file.endswith('.txt')]
        allTxtDocs = '<eod>'.join([self._readFile(file) for file in dataFiles])
        allTxtDocsTok = StanfordTokenizer().tokenize(allTxtDocs)
        unqVoc_LookUp = self._buildVocLookUp(allTxtDocsTok)
        allTxtDocs_allseq = '||*||'.join(allTxtDocsTok).split('<eos>')
        allTxtDocs_byseq = [seq.split('||*||') for seq in allTxtDocs_allseq]
        allTxtDocs_byseq = [list(filter(None, seq)) for seq in allTxtDocs_byseq]
        for seq in allTxtDocs_byseq: seq.append('<eos>')
        allTxtDocsTok_OHE = [self._oneHotEncode(seq, unqVoc_LookUp) for seq in allTxtDocs_byseq]
        return allTxtDocsTok_OHE
    #end
#end
