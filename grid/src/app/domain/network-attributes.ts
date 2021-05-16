import { Country } from './country';
import { NetworkType } from './network-type';
import { NetworkScope } from './network-scope';
import { Integrity } from './integrity';
import { Timestamp } from './timestamp';
import { LatLonImpl } from './lat-lon-impl';

export interface NetworkAttributes {
  readonly id: number;
  readonly country: Country;
  readonly networkType: NetworkType;
  readonly networkScope: NetworkScope;
  readonly name: string;
  readonly km: number;
  readonly meters: number;
  readonly nodeCount: number;
  readonly routeCount: number;
  readonly brokenRouteCount: number;
  readonly brokenRoutePercentage: string;
  readonly integrity: Integrity;
  readonly unaccessibleRouteCount: number;
  readonly connectionCount: number;
  readonly lastUpdated: Timestamp;
  readonly relationLastUpdated: Timestamp;
  readonly center: LatLonImpl;
}
