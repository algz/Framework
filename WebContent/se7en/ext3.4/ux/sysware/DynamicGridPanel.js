Ext.grid.DynamicGridPanel = Ext.extend(Ext.grid.GridPanel, {
			initComponent : function() {

				if (this.columns == null && this.cm == null)
					this.columns = [{
								id : '',
								dataIndex : '',
								width : 100
							}];

				if (this.mapping == null) {
					this.mapping = [{
								name : '',
								mapping : '',
								type : 'string'
							}];
					this.requestMapping = true;
				}

				this.store = new Ext.data.Store({
							url : this.url,
							reader : new Ext.data.JsonReader({
										root : 'rows',
										totalProperty : 'rowCount',
										id : 'id'
									}, this.mapping),
							baseParams : {
								gridId : this.gridId,
								primaryFile : this.primaryFile
							},
							remoteSort : true
						});

				this.bbar = new Ext.PagingToolbar({
							pageSize : 25,
							store : this.store,
							displayInfo : true
						});

				Ext.grid.DynamicGridPanel.superclass.initComponent.call(this);

				this.store.on("metachange", this.onMetaChange, this);

				if (this.editLink) {
					this.on('rowdblclick', this.onRowDblClick, this);
				}
			},
			onRowDblClick : function(record) {
				var record = (record && record.data) ? record : this
						.getSelectionModel().getSelected();
				var redirect = loadContent(this.editLink + '?id='
						+ record.data.id);
				redirect();
			},
			onMetaChange : function(store, meta) {
				var columns = [];
				var autoExpand = false;
				var field = null;
				for (var i = 0; i < meta.fields.length; i++) {
					field = meta.fields[i];
					if (field.header !== undefined) {
						field.dataIndex = field.mapping;
						if (!field.id) {
							field.id = 'c' + i;
						}
						if (!field.width) {
							field.width = 100;
						} else if (field.width == "auto") {
							autoExpand = field.id;
							field.width = 100;
						} else if (typeof field.width == 'string') { // I
																		// accidently
																		// return
																		// the
																		// width
																		// as
																		// string
							field.width = parseInt(field.width); // so
																	// convert
																	// it to int
																	// values
						}
						delete field.name;
						columns[columns.length] = field;
					}
				}
				var cm = new Ext.grid.ColumnModel(columns);
				this.reconfigure(this.store, cm);
			}
		});
Ext.reg('dynamicgrid', Ext.grid.DynamicGridPanel);