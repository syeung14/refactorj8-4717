import org.apache.catalina.*;
import org.apache.catalina.startup.*;
import org.apache.ofbiz.accounting.invoice.*;
import org.apache.ofbiz.accounting.ledger.*;
import org.apache.ofbiz.accounting.payment.*;
import org.apache.ofbiz.accounting.tax.*;
import org.apache.ofbiz.accounting.thirdparty.authorizedotnet.*;
import org.apache.ofbiz.accounting.thirdparty.gosoftware.*;
import org.apache.ofbiz.base.component.*;
import org.apache.ofbiz.base.config.*;
import org.apache.ofbiz.base.container.*;
import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.base.util.cache.*;
import org.apache.ofbiz.base.util.collections.*;
import org.apache.ofbiz.base.util.function.ThrowingFunction;
import org.apache.ofbiz.base.util.string.*;
import org.apache.ofbiz.catalina.container.*;
import org.apache.ofbiz.common.*;
import org.apache.ofbiz.common.authentication.*;
import org.apache.ofbiz.content.*;
import org.apache.ofbiz.content.cms.*;
import org.apache.ofbiz.content.data.*;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.entity.condition.*;
import org.apache.ofbiz.entity.datasource.*;
import org.apache.ofbiz.entity.finder.*;
import org.apache.ofbiz.entity.jdbc.*;
import org.apache.ofbiz.entity.model.*;
import org.apache.ofbiz.entity.util.*;
import org.apache.ofbiz.entityext.*;
import org.apache.ofbiz.entityext.data.*;
import org.apache.ofbiz.entityext.eca.*;
import org.apache.ofbiz.minilang.method.*;
import org.apache.ofbiz.minilang.method.envops.*;
import org.apache.ofbiz.order.order.*;
import org.apache.ofbiz.order.shoppingcart.*;
import org.apache.ofbiz.order.shoppingcart.product.*;
import org.apache.ofbiz.order.shoppingcart.shipping.*;
import org.apache.ofbiz.product.feature.*;
import org.apache.ofbiz.security.*;
import org.apache.ofbiz.service.*;
import org.apache.ofbiz.service.engine.*;
import org.apache.ofbiz.testtools.*;
import org.apache.ofbiz.webapp.control.*;
import org.apache.ofbiz.widget.model.*;
import org.apache.ofbiz.widget.renderer.*;
import org.w3c.dom.*;
import org.apache.ofbiz.base.util.function.Throwings;

import javax.el.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

public class RefactoringTasks {
    /**
     * REFACTOR: Replace with List.sort
     * {@link AuthHelper#loadAuthenticators_internal(LocalDispatcher)}
     * {@link BillingAccountWorker#makePartyBillingAccountList(GenericValue, String, String, Delegator, LocalDispatcher)}
     * {@link ContentJsonEvents#getContentAssocs(HttpServletRequest, HttpServletResponse)}
     * {@link EntityUtil#orderBy(Collection, List)}
     * {@link OrderMapList#exec(MethodContext)}
     * {@link ProductDisplayWorker#productOrderByMap(List, Map, boolean)}
     * {@link ShoppingCart#getLineListOrderedByBasePrice(boolean)}
     * {@link UtilMisc#sortMaps(List, List)}
     */
    public static void task1_defaultMethodsInInterfaces() {
    }

    /**
     * REFACTOR: Replace anonymous type with lambda
     * {@link DataResourceWorker#getDataResourceContentUploadPath(String, double, boolean)}
     * {@link EntityFunction.LENGTH.FETCHER}
     * {@link EntityFunction.TRIM.FETCHER}
     * {@link EntityFunction.UPPER.FETCHER}
     * {@link EntityFunction.LOWER.FETCHER}
     * {@link ModelWidgetCondition.DefaultConditionFactory.TRUE}
     * {@link ModelWidgetCondition.DefaultConditionFactory.FALSE}
     * {@link SSLUtil#getHostnameVerifier(int)} (x2)
     * {@link AuthHelper#getContextClassLoader()} (tricky)
     * {@link ContentJsonEvents#getContentAssocs(HttpServletRequest, HttpServletResponse)}  (Optional))
     * REFACTOR: Replace statement lambda with expression lambda
     * {@link DelegatorEcaHandler#setDelegator(Delegator)}
     */
    public static void task2_replaceAnonymousTypeWithLambda() {
    }

    /**
     * REFACTOR: Replace lambda with method reference
     * {@link CatalinaContainer#init(List, String, String)}
     * {@link CatalinaContainer#prepareVirtualHost(Tomcat, List)}
     * {@link CatalinaContainer#prepareTomcatConnectors(ContainerConfig.Configuration)}
     * {@link ContainerLoader#filterContainersHavingMatchingLoaders(List, Collection)}
     * {@link EntityDataLoadContainer#init(List, String, String)}
     * {@link EntityUtil#filterByCondition(List, EntityCondition)}
     * {@link GenericDelegator#initEntityEcaHandler()}
     * {@link GenericDelegator#initDistributedCacheClear()}
     * {@link MapContext#entrySet()} (x2)
     * {@link MultivaluedMapContextAdapter#entrySet()}
     * {@link ShippingEvents#getGeoIdFromPostalContactMech(Delegator, GenericValue)}
     * {@link TestRunContainer#init(List, String, String)}
     * {@link UtilMisc#toMap(Object...)}
     */
    public static void task3_replaceLambdaWithMethodReference() {
    }

    /**
     * REFACTOR: Replace with Map.forEach()
     * {@link MultivaluedMapContextAdapter#putAll(Map)}
     * {@link CatalinaContainer#prepareContext(Host, ContainerConfig.Configuration, ComponentConfig.WebappInfo, ContainerConfig.Configuration.Property)}
     * REFACTOR: Replace with Iterable.forEach()
     * {@link AbstractEngine#sendCallbacks(ModelService, Map, int)}
     * {@link AbstractEngine#sendCallbacks(ModelService, Map, Throwable, int)}
     * {@link AbstractEngine#sendCallbacks(ModelService, Map, Map, int)}
     * REFACTOR: Bonus, extract common code into separate method,
     * passing in a Consumer of GenericServiceCallback
     */
    public static void task4_replaceLoopWithForEach() {
    }

    /**
     * REFACTOR: Replace loop with removeIf()
     * {@link ShoppingCart#clearPaymentMethodsById(List)}
     * {@link ShoppingCart#cleanUpShipGroups()} (two loops)
     * {@link ShoppingCart#removeFreeShippingProductPromoAction(GenericPK)}
     * {@link ShoppingCart#clearAllPromotionAdjustments()}
     * {@link ShoppingCartItem#removeFeatureAdjustment(String)}
     */
    public static void task5_replaceLoopWithRemoveIf() {
    }

    /**
     * REFACTOR: Replace with Map.getOrDefault method call
     * {@link AIMRespPositions#getPosition(String)}
     * {@link CPRespPositions#getPosition(String)}
     * {@link RequestHandler#renderView(String, boolean, HttpServletRequest, HttpServletResponse, String)}
     * {@link TaxAuthorityServices#rateProductTaxCalc(DispatchContext, Map)}
     * REFACTOR: Replace with Map.putIfAbsent method call
     * {@link OrderReturnServices#createPaymentApplicationsFromReturnItemResponse(DispatchContext, Map)}
     * {@link org.apache.ofbiz.base.conversion.Converters#getConverter(Class, Class)} (x2)
     * REFACTOR: Replace with Map.merge method call
     * {@link ShoppingCartItem#resetPromoRuleUse(String, String)}
     * {@link ShoppingCartItem#confirmPromoRuleUse(String, String)}
     * {@link OrderReadHelper#getOrderNonReturnedTaxAndShipping()}
     * REFACTOR: Replace with Map.computeIfAbsent method call
     * {@link UtilTimer#getTimer(String, boolean)}
     * {@link UtilCache#getNextDefaultIndex(String)}
     * {@link DelegatorFactory#getDelegatorFuture(String)}
     * {@link GenericDAO#getGenericDAO(GenericHelperInfo)}
     * {@link ContentManagementWorker#getStaticValue(Delegator, String, String, boolean)}
     * {@link DatabaseUtil#getColumnInfo(Set, boolean, Collection, ExecutorService)}
     * {@link EntityEcaUtil#readConfig(String, Map)}
     * {@link FindServices#prepareField(Map, Map, Map)} (x3)
     * {@link ModelReader#buildEntity(ResourceHandler, Element, int, ModelInfo)}
     * {@link ModelReader#rebuildResourceHandlerEntities()}
     * {@link ModelReader#getEntitiesByPackage(Set, Set)}
     * {@link ParametricSearch#makeCategoryFeatureLists(String, Delegator, int)}
     * {@link ShoppingCartServices#loadCartFromQuote(DispatchContext, Map)}
     */
    public static void task6_replaceWithCompoundMapMethods() {
    }

    /**
     * REFACTOR: Replace with stream(), anyMatch()
     * {@link ModelEntity#getHasFieldWithAuditLog()}
     * {@link ProductPromoWorker#hasOrderTotalCondition(GenericValue, Delegator)}
     * REFACTOR: Replace with stream(), allMatch()
     * {@link FileUtil.SearchTextFilesFilter#accept(File, String)}
     * {@link ModelEntity#areFields(Collection)}
     * {@link EntityJoinOperator#isEmpty(List)}
     * REFACTOR: Replace with stream(), noneMatch()
     * {@link LoginWorker#hasApplicationPermission(ComponentConfig.WebappInfo, Security, GenericValue)}
     * {@link PcChargeApi#checkIn(String)}
     * {@link PcChargeApi#checkOut(String)}
     */
    public static void task7_replaceWithAllAnyNoneMatch() {
    }

    /**
     * REFACTOR: Replace with stream(), map(), collect()
     * {@link ModelEntity#getFieldNamesFromFieldVector(List)}
     * {@link ModelReader#getEntityCache()}
     * {@link DelegatorContainer#start()}
     * {@link ContainerConfig#getConfigurationPropsFromXml(URL)}
     * REFACTOR: Bonus, replace with stream(), map(), distinct() and count()
     * {@link PaymentGatewayServices#capturePaymentsByInvoice(DispatchContext, Map)}
     */
    public static void task8_replaceWithMapCollect() {
    }

    /**
     * REFACTOR: Replace with stream(), map(), collect() and Collectors.toCollection()
     * {@link EntityJoinOperator#freeze(List)}
     * {@link UtilCache#values()}
     * {@link UtilDateTime.TimeZoneHolder#getTimeZones()}
     */
    public static void task9_replaceWithMapCollectToCollection() {
    }

    /**
     * REFACTOR: Replace with stream(), map(), filter(), collect()
     * {@link EntityDataLoader#getUrlByComponentList(String, List)}
     * {@link EntityFinderUtil.ConditionList#createCondition(Map, ModelEntity, ModelFieldTypeReader)}
     * REFACTOR: Replace with stream(), filter() x 2, collect()
     * {@link ContainerConfig.Configuration#getPropertiesWithValue(String)}
     * REFACTOR two loops to use stream(), map(), filter() x 2, distinct() and forEach()
     * {@link ModelReader#getEntityCache()}
     */
    public static void task10_replaceWithMapFilterCollect() {
    }

    /**
     * REFACTOR: Replace with stream(), collect(), Collectors.toMap()
     * {@link CheckOutHelper#makeBillingAccountMap(List)}
     * {@link ComponentConfig#ComponentConfig(String, String)}
     * {@link MenuFactory#readMenuDocument(Document, String, VisualTheme)}
     * {@link ModelScreenWidget.DecoratorScreen#DecoratorScreen(ModelScreen, Element)}
     * REFACTOR: Bonus, this is an interesting one, as we can combine parts of these
     *  two if clauses once we've converted to stream()
     * {@link UtilMisc.LocaleHolder#getAvailableLocaleList()}
     */
    public static void task11_replaceWithStreamCollectToMap() {
    }

    /**
     * REFACTOR: Replace with stream(), map(), reduce()
     * {@link GeneralLedgerServices#calculateCostCenterTotal(Map)}
     * {@link InvoiceServices#updatePaymentApplicationDefBd(DispatchContext, Map)}
     * {@link OrderReadHelper#calcOrderPromoAdjustmentsBd(List)}
     */
    public static void task12_replaceWithReduce() {
    }

    /**
     * REFACTOR: Replace with stream(), filter(), map(), flatMap(), collect()
     * {@link ComponentConfig#getAllClasspathInfos(String)}
     * {@link ComponentConfig#getAllConfigurations(String)}
     * {@link ComponentConfig#getAllKeystoreInfos(String)}
     * {@link ComponentConfig#getAllTestSuiteInfos(String)}
     * {@link ComponentConfig#getAllWebappResourceInfos(String)}
     * REFACTOR: Extract common method for all the above methods, taking a
     * Function<ComponentConfig, List<E>> as a parameter
     */
    public static void task13_replaceWithFlatMap() {
    }

    /**
     * REFACTOR: Replace with stream(), filter(), findFirst(), orElse()
     * {@link ModelRelation#findKeyMap(String)}
     * {@link ModelRelation#findKeyMapByRelated(String)}
     * REFACTOR: Replace with stream(), filter(), findFirst(), ifPresent()
     * {@link ShoppingCartItem#updatePrice(LocalDispatcher, ShoppingCart)}
     * REFACTOR: Replace with Optional (for the null), map() on Optional, then
     *  stream(), map(), collect() and lastly orElseGet() on the Optional
     * {@link OrderReadHelper#getShippableSizes(String)}
     */
    public static void task14_replaceFindFirstOrAny() {
    }

    /**
     * REFACTOR: Replace with collect(), Collectors.groupingBy() and Collectors.mapping()
     * {@link ModelReader#rebuildResourceHandlerEntities()}
     */
    public static void task15_replaceWithCollectGroupingByMapping() {
    }

    /**
     * REFACTOR: Replace with stream(), flatMap(), checked exceptions, Collectors.toCollection()
     *  Use our {@link Throwings#function(ThrowingFunction)} to convert the Function to
     *  a ThrowingFunction.
     * {@link EntityUtil#getRelated(String, Map, List, boolean)}
     * REFACTOR: Replace with stream(), filter() x 2, collect() with groupingBy() and mapping()
     * {@link ModelReader#getEntitiesByPackage(Set, Set)}
     */
    public static void task16_checkedExceptions() {
    }

    /**
     * REFACTOR to generate stream from ServiceLoader (hint, ServiceLoader has a stream() method,
     *  loop use map(), filter(), findFirst() and Optional.orElseThrow() to complete refactoring
     * {@link UtilObject#getObjectFromFactory(Class, Object)}
     * REFACTOR write a NodeSpliterator that wraps NodeList and then create the result list using
     *  StreamSupport.stream() and collect()
     * {@link NodeELResolver#getValue(ELContext, Object, Object)}
     * REFACTOR by creating a Predicate<ModelField> that works for both if-clauses and then use
     *  stream(), filter(), collect(), Collectors.toCollection()
     * {@link ModelViewEntity#getGroupBysCopy(List)}
     * REFACTOR to use stream(), filter() and anyMatch() (difficult)
     * {@link EntityGroupUtil#getModelEntitiesFromRecords(List, Delegator, boolean)}
     * REFACTOR: Replace with stream(), map(), collect(), toUnmodifiableList() (Java 10+)
     * {@link AbstractModelAction#readSubActions(ModelWidget, Element)}
     * REFACTOR: Replace with Collections.emptySet(), stream(), filter(), map(), collect()
     * {@link ModelGroupReader#getEntityNamesByGroup(String, String)}
     */
    public static void task99_forTheSuperKeen() {
    }
}
