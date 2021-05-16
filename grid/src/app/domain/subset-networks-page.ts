import { SubsetInfo } from './subset-info';
import { NetworkAttributes } from './network-attributes';

export interface SubsetNetworksPage {
  readonly subsetInfo: SubsetInfo;
  readonly km: number;
  readonly networkCount: number;
  readonly nodeCount: number;
  readonly routeCount: number;
  readonly brokenRouteNetworkCount: number;
  readonly brokenRouteNetworkPercentage: string;
  readonly brokenRouteCount: number;
  readonly brokenRoutePercentage: string;
  readonly unaccessibleRouteCount: number;
  readonly analysisUpdatedTime: string;
  readonly networks: Array<NetworkAttributes>;
}
