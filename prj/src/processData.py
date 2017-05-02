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

class ProcessData:

    def __init__():
        '''
        desc:
        args:
        returns:
        '''
        dtm_builder()
    #end

    def _readFile(file):
        '''
        desc:
        args:
        returns:
        '''
        with open(file) as data:
            txtDoc = data.read()
        txtDoc = re.sub('[.!?]\s+', '<eos>', txtDoc)
        txtDoc = re.sub('[,-]', '', txtDoc)
        return txtDoc
    #end

    def _buildVocLookUp(txtDocTok):
        '''
        desc:
        args:
        returns:
        '''
        unqVoc = sorted(set(txtDocTok))
        unqVoc_LookUp = {v:k for k,v in enumerate(unqVoc)}
        return unqVoc_LookUp
    #end

    def _oneHotEncode(txtDocTok, unqVoc_LookUp):
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

    def oneHotDecode(inputOHE, unqVoc_LookUp):
        '''
        desc: decodes single one hot encoded input value
        args:
        returns:
        '''
        indexOfclass = np.argmax(inputOHE)
        decodedClass = list(unqVoc_LookUp.keys())[list(unqVoc_LookUp.values()).index(indexOfclass)]
        return decodedClass
    #end

    def dtm_builder():
        '''
        desc:
        args:
        returns:
        '''
        dataFiles = ['{}/{}'.format(DATA_DIR, file) for file in os.listdir(DATA_DIR) if file.endswith('.txt')]
        allTxtDocs = '<eod>'.join([_readFile(file) for file in dataFiles])
        allTxtDocsTok = StanfordTokenizer().tokenize(allTxtDocs)
        unqVoc_LookUp = _buildVocLookUp(allTxtDocsTok)
        allTxtDocsTok_OHE = _oneHotEncode(allTxtDocsTok, unqVoc_LookUp)
        #allTxtDocs_OHD = [oneHotDecode(exp, unqVoc_LookUp) for exp in allTxtDocsTok_OHE]
    #end
#end
