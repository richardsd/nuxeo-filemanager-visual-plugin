package pt.inevo.filemanager;

/*
 * (C) Copyright 2009-2016 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Ricardo Dias
 *     Miguel Nixo
 */

import org.nuxeo.ecm.core.api.*;
import org.nuxeo.ecm.platform.filemanager.service.extension.AbstractFileImporter;
import org.nuxeo.ecm.platform.types.TypeManager;

import java.io.IOException;
import java.io.Serializable;

public class VisualPlugin extends AbstractFileImporter {

    private static final long serialVersionUID = 1L;
    //private static final Log log = LogFactory.getLog(VisualPlugin.class);

    @Override
    public DocumentModel create(CoreSession session, Blob content, String path,
                                boolean overwrite, String filename, TypeManager typeService)
            throws ClientException, IOException {

        DocumentModel doc = createDocType(session, path, content, filename, "Visual");
        doc = session.createDocument(doc);
        session.save();
        return doc;
    }

    private DocumentModel createDocType(CoreSession session, String path, Blob content, String filename, String type) {
        DocumentModel doc = session.createDocumentModel(path,type,type);
        doc.setPropertyValue("dc:title",filename);
        doc.setPropertyValue("file:content", (Serializable) content);
        doc.setPropertyValue("file:filename",filename);
        return doc;
    }

}

